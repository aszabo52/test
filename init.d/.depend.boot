TARGETS = mountkernfs.sh hostname.sh udev keyboard-setup mountdevsubfs.sh hwclock.sh lvm2 checkroot.sh console-setup nfs-common rpcbind networking mountall.sh mountall-bootclean.sh urandom mountnfs.sh mountnfs-bootclean.sh checkfs.sh checkroot-bootclean.sh kbd kmod bootmisc.sh x11-common procps udev-finish
INTERACTIVE = udev keyboard-setup checkroot.sh console-setup checkfs.sh kbd
udev: mountkernfs.sh
keyboard-setup: mountkernfs.sh udev
mountdevsubfs.sh: mountkernfs.sh udev
hwclock.sh: mountdevsubfs.sh
lvm2: mountdevsubfs.sh udev
checkroot.sh: hwclock.sh keyboard-setup mountdevsubfs.sh hostname.sh
console-setup: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh kbd
nfs-common: rpcbind hwclock.sh
rpcbind: networking mountall.sh mountall-bootclean.sh
networking: mountkernfs.sh mountall.sh mountall-bootclean.sh urandom procps
mountall.sh: checkfs.sh checkroot-bootclean.sh lvm2
mountall-bootclean.sh: mountall.sh
urandom: mountall.sh mountall-bootclean.sh hwclock.sh
mountnfs.sh: mountall.sh mountall-bootclean.sh networking rpcbind nfs-common
mountnfs-bootclean.sh: mountall.sh mountall-bootclean.sh mountnfs.sh
checkfs.sh: lvm2 checkroot.sh
checkroot-bootclean.sh: checkroot.sh
kbd: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
kmod: checkroot.sh
bootmisc.sh: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh udev checkroot-bootclean.sh
x11-common: mountall.sh mountall-bootclean.sh mountnfs.sh mountnfs-bootclean.sh
procps: mountkernfs.sh mountall.sh mountall-bootclean.sh udev
udev-finish: udev mountall.sh mountall-bootclean.sh
