package cn.banny.emulator.arm;

import unicorn.Arm64Const;
import unicorn.ArmConst;
import unicorn.Unicorn;

public class HookStatus {

    final long returnValue;
    final long jump;

    private HookStatus(long returnValue, long jump) {
        this.returnValue = returnValue;
        this.jump = jump;
    }

    public static HookStatus RET(Unicorn u, long pc) {
        return new HookStatus(((Number) u.reg_read(ArmConst.UC_ARM_REG_R0)).intValue(), pc);
    }

    public static HookStatus LR(Unicorn u, int returnValue) {
        return new HookStatus(returnValue, ((Number) u.reg_read(ArmConst.UC_ARM_REG_LR)).intValue() & 0xffffffffL);
    }

    public static HookStatus RET64(Unicorn u, long pc) {
        return new HookStatus(((Number) u.reg_read(Arm64Const.UC_ARM64_REG_X0)).longValue(), pc);
    }

    public static HookStatus LR64(Unicorn u, long returnValue) {
        return new HookStatus(returnValue, ((Number) u.reg_read(Arm64Const.UC_ARM64_REG_LR)).longValue());
    }

}
