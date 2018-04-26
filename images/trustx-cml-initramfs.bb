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
	control \
	run \
	strace \
	iptables \
	${ROOTFS_BOOTSTRAP_INSTALL} \
"

IMAGE_LINUGUAS = " "

LICENSE = "GPLv2"

IMAGE_FEATURES = ""

EXTRA_IMAGE_FEATURES = "debug-tweaks "

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
    echo "1:2345:respawn:${base_sbindir}/mingetty --autologin root tty1" >> ${IMAGE_ROOTFS}/etc/inittab
}

ROOTFS_POSTPROCESS_COMMAND += "update_fstab; update_inittab;"
