FILESEXTRAPATHS_prepend := "${THISDIR}/files:"
#SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/will/kvmtool.git \
#           file://external-crosscompiler.patch \
#           file://0001-Avoid-pointers-for-address-of-packed-members.patch \
#           file://0001-kvmtool-9p-fixed-compilation-error.patch \
#           file://0002-kvmtool-add-EXTRA_CFLAGS-variable.patch
SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/will/kvmtool.git \
           file://external-crosscompiler.patch \
           file://0001-Makefile-remove-march-armv7-a-from-ARCH-arm.patch \
"
SRCREV = "c57e001a3efba729a2d0c424bb7bb570c852f851"
