LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${S}/COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263"

BRANCH = "dunfell"
SRCREV = "${AUTOREV}"

PVBASE := "${PV}"
PV = "${PVBASE}+${SRCPV}"

SRC_URI = "git://github.com/trustm3/device_fraunhofer_common_cml.git;branch=${BRANCH}"

S = "${WORKDIR}/git/"

INSANE_SKIP_${PN} = "ldflags"

DEPENDS = "protobuf-c-native protobuf-c protobuf-c-text rsync-native"

FILES_${PN} += "${base_sbindir}"
INHIBIT_PACKAGE_STRIP = "1"

# Determine if a local checkout of the cml repo is available.
# If so, build using externalsrc.
# If not, build from git.
python () {
    cml_dir = d.getVar('TOPDIR', True) + "/../trustme/cml"
    if os.path.isdir(cml_dir):
        d.setVar('EXTERNALSRC', cml_dir)
        d.setVar('EXTERNALSRC_BUILD', cml_dir)
}
inherit externalsrc


do_configure () {
        :
}

do_compile () {
    if [ -n "${EXTERNALSRC}" ]; then
        rsync -lr --exclude="oe-logs" --exclude="oe-workdir" "${S}/" "${B}"
    fi

        oe_runmake -C service service-static
        oe_runmake -C service exec_cap_systime
}

do_install () {
        :
	install -d ${D}${base_sbindir}/
	install -m 0755 ${B}/service/cml-service-container ${D}${base_sbindir}/
	install -m 0755 ${B}/service/exec_cap_systime ${D}${base_sbindir}/
}
