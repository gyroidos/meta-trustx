FILESEXTRAPATHS_append := '${@bb.utils.contains("INITRAMFS_IMAGE", [ 'trustx-cml-initramfs' ], ":${THISDIR}/${PN}", "",d)}'

SRC_URI__append := '${@bb.utils.contains("INITRAMFS_IMAGE", [ 'trustx-cml-initramfs' ], "file://installer.cfg", "",d)}'
SRC_URI__append := '${@bb.utils.contains("INITRAMFS_IMAGE", [ 'trustx-cml-initramfs' ], "file://static.cfg", "",d)}'
