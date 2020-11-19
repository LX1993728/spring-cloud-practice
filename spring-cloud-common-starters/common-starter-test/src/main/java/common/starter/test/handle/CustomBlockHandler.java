package common.starter.test.handle;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import common.starter.test.domain.CommonResult;

public class CustomBlockHandler {

    public CommonResult handleException(BlockException exception){
        return new CommonResult<>("自定义限流信息",200);
    }
}
