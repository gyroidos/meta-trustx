SRC_URI += " \
	file://0001-video-logo-GyroidOS-specific-logo.patch \
	file://0001-audit-allow-logging-of-user-events-in-non-initial-na.patch \
	file://module_signing.cfg \
	file://trustx.cfg \
"
FILESEXTRAPATHS:prepend := "${THISDIR}/generic:"

DEPENDS += "squashfs-tools-native openssl-native"

MODULE_IMAGE_SUFFIX = "squashfs"

EXTRA_OEMAKE += " INSTALL_MOD_STRIP=1"
KERNEL_EXTRA_ARGS:prepend = "${PARALLEL_MAKE} "

KERNEL_MODULE_SIG_KEY ?= ""
KERNEL_SYSTEM_TRUSTED_KEYS ?= ""

kernel_do_configure:prepend() {
	# replace or add the config option depending of if it already exists in .config
	# sed command reference: https://superuser.com/a/976712
	if [ "" != "${KERNEL_MODULE_SIG_KEY}" ]; then
		sed -i '/^CONFIG_MODULE_SIG_KEY=/{h;s|=.*|="${KERNEL_MODULE_SIG_KEY}"|};${x;/^$/{s||CONFIG_MODULE_SIG_KEY="${KERNEL_MODULE_SIG_KEY}"|;H};x}' ${B}/.config
	fi

	if [ "" != "${KERNEL_SYSTEM_TRUSTED_KEYS}" ]; then
		sed -i '/^CONFIG_SYSTEM_TRUSTED_KEYS=/{h;s|=.*|="${KERNEL_SYSTEM_TRUSTED_KEYS}"|};${x;/^$/{s||CONFIG_SYSTEM_TRUSTED_KEYS="${KERNEL_SYSTEM_TRUSTED_KEYS}"|;H};x}' ${B}/.config
	fi
}
