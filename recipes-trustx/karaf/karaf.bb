DESCRIPTION = "Karaf Tool for Core Container"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=86d3f3a95c324c9479bd8986968f4327"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "file://offline-karaf-1.1.0-SNAPSHOT.tar.gz"

S = "${WORKDIR}/offline-karaf-1.1.0-SNAPSHOT"

do_install() {
  install -d ${D}/datafs/karaf
  cp -r ${S}/* ${D}/datafs/karaf
}

FILES_${PN} += "/datafs/karaf/* "
