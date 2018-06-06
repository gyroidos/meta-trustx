SUMMARY = "efitools"
DESCRIPTION = "EFI tools to generate an deploy platform keys"
HOMEPAGE = "https://git.kernel.org/pub/scm/linux/kernel/git/jejb/efitools.git/"
SECTION = "console/tools"
LICENSE = "GPLv2"

LIC_FILES_CHKSUM = "file://COPYING;md5=e28f66b16cb46be47b20a4cdfe6e99a1"

SRC_URI[md5sum] = "5f20ed9cccf786cf6525c54537ae7831"
SRC_URI[sha256sum] = "d48b66768374ce742971c44e475b7336992b3d0a51407920a2e3876e8d9fc329"

SRC_URI = "https://git.kernel.org/pub/scm/linux/kernel/git/jejb/efitools.git/snapshot/efitools_${PV}.tar.gz"

inherit native

DEPENDS = "gnu-efi-native help2man-native sbsigntool-native"

S = "${WORKDIR}/efitools_${PV}"


LIBRARY_FLAGS = "\
	-nostdlib -shared -Bsymbolic \
	${STAGING_LIBDIR}/crt0-efi-${HOST_ARCH}.o \
	-L ${STAGING_LIBDIR} \
	-T ${STAGING_LIBDIR}/elf_${HOST_ARCH}_efi.lds \
"

#tmp/sysroots-components/x86_64/gnu-efi-native/usr/lib/crt0-efi-x86_64.o

INCLUDES = "\
	-I${S}/include \
	-I${STAGING_INCDIR} \
	-I${STAGING_INCDIR}/efi \
	-I${STAGING_INCDIR}/efi/${HOST_ARCH} \
	-I${STAGING_INCDIR}/efi/protocol \
"

# overwrite compiler and compiler flags in makefile
EXTRA_OEMAKE = "\
        'CC = ${CC} -L ${STAGING_LIBDIR}' \
        'LD = ${LD}' \
	'INCDIR = ${INCLUDES}' \
	'LDFLAGS = ${LIBRARY_FLAGS}' \
"

do_compile() {
	oe_runmake all
}

do_install () {
	install -d ${D}${bindir}
	install ${S}/cert-to-efi-sig-list ${D}${bindir}
	install ${S}/sig-list-to-certs ${D}${bindir}
	install ${S}/sign-efi-sig-list ${D}${bindir}
	install ${S}/hash-to-efi-sig-list ${D}${bindir}
	install ${S}/cert-to-efi-hash-list ${D}${bindir}
	install ${S}/KeyTool.efi ${D}${bindir}
	install ${S}/LockDown.efi ${D}${bindir}
}
