SRC_URI += "\
	file://5.4/0001-lib-vdso-Let-do_coarse-return-0-to-simplify-the-call.patch \
	file://5.4/0002-lib-vdso-Avoid-duplication-in-__cvdso_clock_getres.patch \
	file://5.4/0003-lib-vdso-Add-unlikely-hint-into-vdso_read_begin.patch \
	file://5.4/0004-lib-vdso-Mark-do_hres-and-do_coarse-as-__always_inli.patch \
	file://5.4/0005-ns-Introduce-Time-Namespace.patch \
	file://5.4/0006-time-Add-timens_offsets-to-be-used-for-tasks-in-time.patch \
	file://5.4/0007-posix-clocks-Rename-the-clock_get-callback-to-clock_.patch \
	file://5.4/0008-posix-clocks-Rename-.clock_get_timespec-callbacks-ac.patch \
	file://5.4/0009-alarmtimer-Rename-gettime-callback-to-get_ktime.patch \
	file://5.4/0010-alarmtimer-Provide-get_timespec-callback.patch \
	file://5.4/0011-posix-clocks-Introduce-clock_get_ktime-callback.patch \
	file://5.4/0012-posix-timers-Use-clock_get_ktime-in-common_timer_get.patch \
	file://5.4/0013-posix-clocks-Wire-up-clock_gettime-with-timens-offse.patch \
	file://5.4/0014-time-Add-do_timens_ktime_to_host-helper.patch \
	file://5.4/0015-timerfd-Make-timerfd_settime-time-namespace-aware.patch \
	file://5.4/0016-posix-timers-Make-timer_settime-time-namespace-aware.patch \
	file://5.4/0017-alarmtimer-Make-nanosleep-time-namespace-aware.patch \
	file://5.4/0018-hrtimers-Prepare-hrtimer_nanosleep-for-time-namespac.patch \
	file://5.4/0019-posix-timers-Make-clock_nanosleep-time-namespace-awa.patch \
	file://5.4/0020-fs-proc-Respect-boottime-inside-time-namespace-for-p.patch \
	file://5.4/0021-x86-vdso-Restrict-splitting-VVAR-VMA.patch \
	file://5.4/0022-lib-vdso-Prepare-for-time-namespace-support.patch \
	file://5.4/0023-x86-vdso-Provide-vdso_data-offset-on-vvar_page.patch \
	file://5.4/0024-x86-vdso-Add-time-napespace-page.patch \
	file://5.4/0025-time-Allocate-per-timens-vvar-page.patch \
	file://5.4/0026-x86-vdso-Handle-faults-on-timens-page.patch \
	file://5.4/0027-x86-vdso-On-timens-page-fault-prefault-also-VVAR-pag.patch \
	file://5.4/0028-x86-vdso-Zap-vvar-pages-when-switching-to-a-time-nam.patch \
	file://5.4/0029-fs-proc-Introduce-proc-pid-timens_offsets.patch \
	file://5.4/0030-selftests-timens-Add-Time-Namespace-test-for-support.patch \
	file://5.4/0031-selftests-timens-Add-a-test-for-timerfd.patch \
	file://5.4/0032-selftests-timens-Add-a-test-for-clock_nanosleep.patch \
	file://5.4/0033-selftests-timens-Add-procfs-selftest.patch \
	file://5.4/0034-selftests-timens-Add-timer-offsets-test.patch \
	file://5.4/0035-selftests-timens-Add-a-simple-perf-test-for-clock_ge.patch \
	file://5.4/0036-selftests-timens-Check-for-right-timens-offsets-afte.patch \
	file://5.4/0037-time-namespace-Fix-time_for_children-symlink.patch \
	file://5.4/0038-proc-time-namespace-Show-clock-symbolic-names-in-pro.patch \
	file://5.4/0039-selftests-timens-Remove-duplicated-include-time.h.patch \
	file://5.4/0040-selftests-timens-handle-a-case-when-alarm-clocks-are.patch \
	file://5.4/0041-futex-Adjust-absolute-futex-timeouts-with-per-time-n.patch \
	file://5.4/0042-selftests-timens-Add-a-test-for-futex.patch \
"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
