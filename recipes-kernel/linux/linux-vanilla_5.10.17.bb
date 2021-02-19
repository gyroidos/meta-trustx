inherit kernel
require recipes-kernel/linux/linux-yocto.inc

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRC_URI = "https://cdn.kernel.org/pub/linux/kernel/v5.x/linux-${PV}.tar.xz"

SRC_URI[md5sum] = "4908707ed841923d8d1814130d5c380f"
SRC_URI[sha256sum] = "e84e623ce8bb2446ec026b62eafa3b18480aa6fb6ae9c86cd8f18651324d4814"

LINUX_VERSION ?= "${PV}"
LINUX_VERSION_EXTENSION ?= "-vanilla"

SRCREV="${AUTOREV}"

DEPENDS += "elfutils-native"

COMPATIBLE_MACHINE = "${MACHINE}"

S = "${WORKDIR}/linux-${PV}"

KBUILD_DEFCONFIG_genericx86-64 = "x86_64_defconfig" 
KCONFIG_MODE="--alldefconfig"

