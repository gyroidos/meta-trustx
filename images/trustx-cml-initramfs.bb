DECRIPTION = "Minimal initramfs-based root file system for CML"

PACKAGE_INSTALL = "\
	base-files \
	base-passwd \
	busybox \
	initscripts \
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

export IMAGE_BASENAME = "trustx-cml-initramfs"
IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"
inherit image

IMAGE_ROOTFS_SIZE = "8192"
