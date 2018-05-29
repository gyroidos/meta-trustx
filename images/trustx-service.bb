# Recipe to build service container

require recipes-core/images/core-image-base.bb

# add your software
IMAGE_INSTALL += "gdb"

IMAGE_FSTYPES += "squashfs"
