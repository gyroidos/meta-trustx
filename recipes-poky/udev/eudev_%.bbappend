FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://60-persistent-storage.rules"
SRC_URI += "file://10-dm.rules"

do_install_append() {
        install -m 0644 ${WORKDIR}/10-dm.rules ${D}${sysconfdir}/udev/rules.d/10-dm.rules
        install -m 0644 ${WORKDIR}/60-persistent-storage.rules ${D}${sysconfdir}/udev/rules.d/60-persistent-storage.rules
}
