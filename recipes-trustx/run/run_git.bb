LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${WORKDIR}/git/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/trustm3/device_fraunhofer_common_cml.git"

S = "${WORKDIR}/git/run/"

INSANE_SKIP_${PN} = "ldflags"

FILES_${PN} += "${sbindir}"
INHIBIT_PACKAGE_STRIP = "1"

do_configure () {
        :
}


do_compile () {
        oe_runmake all 
}

do_install () {
        :
	install -d ${D}${sbindir}/
    	install -m 0755 ${S}/run ${D}${sbindir}/
}
