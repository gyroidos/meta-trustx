inherit image
inherit trustmeinstaller

KERNELVERSION="$(cat "${STAGING_KERNEL_BUILDDIR}/kernel-abiversion")"

DEPENDS += "coreutils-native"

IMAGE_FSTYPES="trustmeinstaller"
