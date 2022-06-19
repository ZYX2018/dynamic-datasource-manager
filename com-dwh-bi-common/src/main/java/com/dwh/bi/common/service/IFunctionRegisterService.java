package com.dwh.bi.common.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwh.bi.common.domain.FunctionRegister;
import com.dwh.bi.common.params.FunctionPageParam;
import com.dwh.bi.common.vo.FunctionRegisterVO;
import com.github.yulichang.base.MPJBaseService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangyx-v
 * @since 2022-06-19
 */
public interface IFunctionRegisterService extends MPJBaseService<FunctionRegister> {
    /**
     *分页查询
     * @param pageParamParam pageParamParam
     * @return DataSourceRegisterVO
     */
    IPage<FunctionRegisterVO> page(FunctionPageParam pageParamParam);
}
