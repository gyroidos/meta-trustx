inherit kernel
require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v5.x/linux-${PV}.tar.xz"

SRC_URI[md5sum] = "d25a92879dd191f38cf1405fd6a90b58"
SRC_URI[sha256sum] = "f728de695ec5eb17efa15acaecc48fcd7a6c4a912b51704ed137cccf93f9f5e0"

LINUX_VERSION ?= "${PV}"
LINUX_VERSION_EXTENSION ?= "-vanilla"

SRCREV="${AUTOREV}"

DEPENDS += "elfutils-native"

COMPATIBLE_MACHINE = "${MACHINE}"

S = "${WORKDIR}/linux-${PV}"

KBUILD_DEFCONFIG_genericx86-64 = "x86_64_defconfig" 
KCONFIG_MODE="--alldefconfig"

