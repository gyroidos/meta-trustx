inherit image

LICENSE = "GPL-2.0-only"

DEPENDS += "coreutils-native"

IMAGE_FSTYPES="${TRUSTME_FSTYPES}"

INITRAMFS_IMAGE_BUNDLE = "1"
INITRAMFS_IMAGE = "trustx-cml-initramfs"

PACKAGE_CLASSES = "package_ipk"

prepare_device_conf () {
    cp "${THISDIR}/${PN}/device.conf" "${WORKDIR}"

    if [ "y" = "${DEVELOPMENT_BUILD}" ];then
        if [ -z "$(grep 'signed_configs' ${WORKDIR}/device.conf)" ];then
            bbwarn "Disabling signature enforcement for container configuration in dev build"
            echo "signed_configs: false" >> ${WORKDIR}/device.conf
        else
            bbwarn "Setting for signed_configs already specified, leaving unchanged..."
        fi
    fi
}
IMAGE_PREPROCESS_COMMAND:append = " prepare_device_conf;"
