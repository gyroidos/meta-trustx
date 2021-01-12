FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " file://installer.cfg "
SRC_URI_append = " file://static.cfg "
