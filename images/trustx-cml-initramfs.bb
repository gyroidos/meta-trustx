DECRIPTION = "Minimal initramfs-based root file system for CML"

PACKAGE_INSTALL = "\
	packagegroup-core-boot \
	${VIRTUAL-RUNTIME_base-utils} \
	udev \
	base-passwd \
	mingetty \
	libselinux \
	cmld \
	scd \
	strace \
	iptables \
	ibmtss2 \
	lvm2 \
	kpartx \
	tpm2d \
	e2fsprogs-mke2fs \
	kvmtool \
	${ROOTFS_BOOTSTRAP_INSTALL} \
"

IMAGE_LINUGUAS = " "

LICENSE = "GPLv2"

IMAGE_FEATURES = ""

export IMAGE_BASENAME = "trustx-cml-initramfs"
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit image

IMAGE_FEATURES_remove += "package-management"

IMAGE_ROOTFS_SIZE = "4096"

update_fstab () {
    mkdir -p ${IMAGE_ROOTFS}/data
    cat >> ${IMAGE_ROOTFS}/etc/fstab <<EOF

/dev/disk/by-partlabel/boot /boot vfat umask=0077 0 1 
/dev/disk/by-partlabel/data /data ext4 defaults   0 0 

EOF
}

update_inittab () {
    echo "12:2345:respawn:${base_sbindir}/mingetty --autologin root tty12" >> ${IMAGE_ROOTFS}/etc/inittab
    mkdir -p ${IMAGE_ROOTFS}/dev
    mknod -m 622 ${IMAGE_ROOTFS}/dev/console c 5 1
    mknod -m 622 ${IMAGE_ROOTFS}/dev/tty12 c 4 1
}

ROOTFS_POSTPROCESS_COMMAND += "update_fstab; update_inittab;"

## uncomment for debug purpose to allow login
#inherit extrausers
#EXTRA_USERS_PARAMS = "usermod -P root root;"
