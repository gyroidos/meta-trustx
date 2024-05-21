inherit image_types
inherit kernel-artifact-names

#
# Create an partitioned trustme image that can be dd'ed to the boot medium
#


TEST_CERT_DIR = "${TOPDIR}/test_certificates"
SECURE_BOOT_SIGNING_KEY = "${TEST_CERT_DIR}/ssig_subca.key"
SECURE_BOOT_SIGNING_CERT = "${TEST_CERT_DIR}/ssig_subca.cert"

TRUSTME_IMAGE_OUT="${B}/trustme_image"
TRUSTME_IMAGE="${TRUSTME_IMAGE_OUT}/trustmeimage.img"

TRUSTME_GENERIC_DEPENDS = " \
    trustx-cml-initramfs:do_image_complete \
    trustx-cml-modules:do_image_complete \
    trustx-cml-firmware:do_image_complete \
    virtual/kernel:do_deploy \
"

DEPENDS += "e2fsprogs-native bc-native"

do_rootfs () {
	if [ -z "${TRUSTME_IMAGE_OUT}" ];then
		bbfatal_log "Cannot get bitbake variable \"TRUSTME_IMAGE_OUT\""
		exit 1
	fi

	if [ -z "${TRUSTME_CONTAINER_ARCH}" ];then
		bbfatal_log "Cannot get bitbake variable \"TRUSTME_CONTAINER_ARCH\""
		exit 1
	fi

	rm -f "${TRUSTME_IMAGE}"

	machine=$(echo "${MACHINE}" | tr "-" "_")

	bbnote "Starting to create trustme image"
	# create temporary directories
	install -d "${TRUSTME_IMAGE_OUT}"
	rootfs="${IMAGE_ROOTFS}"
	rootfs_datadir="${rootfs}/userdata/"
	tmpdir="${TOPDIR}/tmp_container"

	rm -fr "${rootfs}/"
	install -d "${rootfs}/"
	rm -fr "${rootfs_datadir}"
	install -d "${rootfs_datadir}"

	install -d "${rootfs_datadir}/cml/tokens"
	install -d "${rootfs_datadir}/cml/containers_templates"

	# define file locations
	#deploy_dir_container = "${tmpdir}/deploy/images/qemu-x86-64"
	containerarch="${TRUSTME_CONTAINER_ARCH}"
	deploy_dir_container="${tmpdir}/deploy/images/$(echo $containerarch | tr "_" "-")"

	src="${TOPDIR}/../trustme/build/"
	config_creator_dir="${src}/config_creator"
	proto_file_dir="${WORKDIR}/cml/daemon"
	provisioning_dir="${src}/device_provisioning"
	enrollment_dir="${provisioning_dir}/oss_enrollment"
	test_cert_dir="${TOPDIR}/test_certificates"
	cfg_overlay_dir="${src}/config_overlay"
	device_cfg="${WORKDIR}/device.conf"

	if ! [ -d "${test_cert_dir}" ];then
		bbfatal_log "Test PKI not generated at ${test_cert_dir}\nIs trustx-cml-userdata built?"
		exit 1
	fi

	# copy files to temp data directory
	bbnote "Preparing files for data partition"

	cp -f "${test_cert_dir}/ssig_rootca.cert" "${rootfs_datadir}/cml/tokens/"
	mkdir -p "${rootfs_datadir}/cml/operatingsystems/"
	mkdir -p "${rootfs_datadir}/cml/containers/"

	if [ -d "${TOPDIR}/../custom_containers" ];then # custom container provided in ${TOPDIR}/../custom_container
		bbnote "Installing custom container and configs to image: ${TOPDIR}/../custom_containers"
		cp -far "${TOPDIR}/../custom_containers/00000000-0000-0000-0000-000000000000.conf" "${rootfs_datadir}/cml/containers_templates/"
		find "${TOPDIR}/../custom_containers/" -name '*os*' -exec cp -afr {} "${rootfs_datadir}/cml/operatingsystems" \;
		cp -f "${TOPDIR}/../custom_containers/device.conf" "${rootfs_datadir}/cml/"
	elif [ -d "${deploy_dir_container}/trustx-guests" ];then # container built in default location
		bbnote "Installing containers from default location ${deploy_dir_container}/trustx-guests"
		cp -far "${deploy_dir_container}/trustx-configs/container/." "${rootfs_datadir}/cml/containers_templates/"
		cp -afr "${deploy_dir_container}/trustx-guests/." "${rootfs_datadir}/cml/operatingsystems"
		cp -f "${device_cfg}" "${rootfs_datadir}/cml/"
	else # no container provided
		bbwarn "It seems that no containers were built in directory ${deploy_dir_container}. You will have to provide at least c0 manually!"
		cp -f "${device_cfg}" "${rootfs_datadir}/cml/"
	fi

	# sign container configs
	find "${rootfs_datadir}/cml/containers_templates" -name '*.conf' -exec bash \
		${enrollment_dir}/config_creator/sign_config.sh {} \
		${TEST_CERT_DIR}/ssig_cml.key ${TEST_CERT_DIR}/ssig_cml.cert \;

	# copy modules to data partition directory
	bbnote "Copying linux-modules"
	cp -fL "${DEPLOY_DIR_IMAGE}/trustx-cml-modules-${MACHINE}.squashfs" "${rootfs}/modules.img"

	# copy firmware to data partition directory
	bbnote "Copying linux-firmware"
	cp -fL "${DEPLOY_DIR_IMAGE}/trustx-cml-firmware-${MACHINE}.squashfs" "${rootfs}/firmware.img"

	# copy kernel update files to data partition directory
	bbnote "Copying kernel update files"
	if ! [ -z "${UPDATE_FILES}" ];then
		for update_file in ${UPDATE_FILES}; do
			if [ -L $update_file ]; then
				real_update_file=$(readlink -f $update_file)
			else
				real_update_file=$update_file
			fi
			cp -fr "$real_update_file" "${rootfs_datadir}/cml/operatingsystems"
		done
	fi
}

ROOTFS_PREPROCESS_COMMAND = ""

do_rootfs[depends] += " ${TRUSTME_GENERIC_DEPENDS} "

IMAGE_POSTPROCESS_COMMAND:append = " deploy_trustmeimage; "

deploy_trustmeimage () {
	ln -sf "../${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.wic" "${TRUSTME_IMAGE_OUT}/trustmeimage.img"
	ln -sf "../${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.wic.bmap" "${TRUSTME_IMAGE_OUT}/trustmeimage.img.bmap"

	# deploy rootfs contents for installer build
	cp -r "${IMAGE_ROOTFS}" "${TRUSTME_IMAGE_OUT}/trustme_datapartition"

	cp -r "${TRUSTME_IMAGE_OUT}" "${IMGDEPLOYDIR}"
}
