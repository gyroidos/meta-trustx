OS_CONFIG = "${THISDIR}/${PN}/${TRUSTME_HARDWARE}/${PN}os.conf"
CONTAINER_CONFIG_FILE = "00000000-0000-0000-0000-000000000000.conf"
CONTAINER_CONFIG = "${THISDIR}/${PN}/${CONTAINER_CONFIG_FILE}"

inherit gyroid-guestos

IMAGE_FEATURES:append = " allow-empty-password"
IMAGE_FEATURES:append = " empty-root-password"
IMAGE_INSTALL:append = " control"
IMAGE_INSTALL:append = " openvswitch"
IMAGE_INSTALL:append = " tcpdump"
