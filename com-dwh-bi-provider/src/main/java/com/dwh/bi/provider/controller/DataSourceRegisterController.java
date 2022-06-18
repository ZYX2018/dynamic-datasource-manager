package com.dwh.bi.provider.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dwh.bi.base.params.RequestObject;
import com.dwh.bi.base.vo.ResultObject;

import com.dwh.bi.common.domain.DataSourceRegister;
import com.dwh.bi.common.params.DataSourcePageParam;
import com.dwh.bi.common.service.impl.DataSourceRegisterServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangyx-v
 * @since 2022-06-18
 */
@Tag(name = "数据源管理",description = "数据源管理")
@RestController
@RequestMapping("/api/data-source-register/")
public class DataSourceRegisterController {

    private final DataSourceRegisterServiceImpl dataSourceRegisterService ;

    public DataSourceRegisterController(DataSourceRegisterServiceImpl dataSourceRegisterService) {
        this.dataSourceRegisterService = dataSourceRegisterService;
    }

    @Operation(description = "增加一个数据源",summary = "saveOrUpdate")
    @Parameters(@Parameter(name = "param" ,description = "数据源",content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestObject.class))}))
    @ApiResponses(@ApiResponse(responseCode = "200",description = "成功",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResultObject.class))} ))
    @PostMapping("saveOrUpdate")
    ResultObject<Boolean> saveOrUpdate(@Valid @RequestBody RequestObject<DataSourceRegister> param){
        return ResultObject.success(dataSourceRegisterService.saveOrUpdate(param.getParams()));
    }

    @Operation(description = "删除一个数据源",summary = "removeDataSourceById")
    @Parameters(@Parameter(name = "dataSourceId",description = "数据源主键",content = {@Content(mediaType = "plain/text",schema = @Schema(implementation = String.class))}))
    @ApiResponses(@ApiResponse(responseCode = "200",description = "成功",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResultObject.class))} ))
    @DeleteMapping("removeById")
    ResultObject<Boolean> remove(@RequestParam("dataSourceId")String dataSourceId){
        return ResultObject.success(dataSourceRegisterService.removeById(dataSourceId));
    }

    @Operation(description = "分页查询数据源",summary = "getDataSourcePage")
    @Parameters(@Parameter(name = "param",description = "数据分页查询参数",content = {@Content(mediaType = "application/json",schema = @Schema(implementation = RequestObject.class))}))
    @ApiResponses(@ApiResponse(responseCode = "200",description = "成功",content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResultObject.class))} ))
    @PostMapping("page")
    ResultObject<Page<DataSourceRegister>> getDataSourcePage(@Valid @RequestBody RequestObject<DataSourcePageParam> param){
        return ResultObject.success(dataSourceRegisterService.lambdaQuery().like(DataSourceRegister::getSourceIp,param.getParams().getIp()).like(DataSourceRegister::getSourceName,param.getParams().getDataSourceName()).page(new Page<>(param.getParams().getPage(),param.getParams().getPageSize())) );
    }
}
