LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

BRANCH = "master"
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/trustm3/device_fraunhofer_common_cml.git;protocol=http;branch=${BRANCH}"

S = "${WORKDIR}/git/"

INSANE_SKIP_${PN} = "ldflags"

DEPENDS = "protobuf-c-native protobuf-c protobuf-c-text ibmtss2"

do_configure () {
        :
}


do_compile () {
        oe_runmake -C tpm2d 
        oe_runmake -C tpm2_control 
}

do_install () {
        :
	install -d ${D}${sbindir}/
    	install -m 0755 ${S}tpm2d/tpm2d ${D}${sbindir}/
    	install -m 0755 ${S}tpm2_control/tpm2_control ${D}${sbindir}/

}
