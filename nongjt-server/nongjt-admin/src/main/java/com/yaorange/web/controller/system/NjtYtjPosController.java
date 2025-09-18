package com.yaorange.web.controller.system;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yaorange.common.annotation.Log;
import com.yaorange.common.core.controller.BaseController;
import com.yaorange.common.core.domain.AjaxResult;
import com.yaorange.common.core.page.TableDataInfo;
import com.yaorange.common.enums.BusinessType;
import com.yaorange.common.utils.poi.ExcelUtil;
import com.yaorange.system.domain.NjtYtjPos;
import com.yaorange.system.service.INjtYtjPosService;

/**
 * 一体打印机服务点操作处理
 *
 * @author ruoyi
 */
@Tag(name = "一体打印机服务点管理")
@RestController
@RequestMapping("/ytj/pos")
public class NjtYtjPosController extends BaseController
{
    @Autowired
    private INjtYtjPosService njtYtjPosService;

    /**
     * 获取一体打印机服务点列表
     */
    @Operation(summary = "获取一体打印机服务点列表")
//    @PreAuthorize("@ss.hasPermi('system:ytjpos:list')")
    @GetMapping("/list")
    public TableDataInfo list(NjtYtjPos njtYtjPos)
    {
        startPage();
        List<NjtYtjPos> list = njtYtjPosService.selectNjtYtjPosList(njtYtjPos);
        return getDataTable(list);
    }

    @Log(title = "一体打印机服务点管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:ytjpos:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NjtYtjPos njtYtjPos)
    {
        List<NjtYtjPos> list = njtYtjPosService.selectNjtYtjPosList(njtYtjPos);
        ExcelUtil<NjtYtjPos> util = new ExcelUtil<NjtYtjPos>(NjtYtjPos.class);
        util.exportExcel(response, list, "一体打印机服务点数据");
    }

    /**
     * 根据主键获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:ytjpos:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(njtYtjPosService.selectNjtYtjPosById(id));
    }

    /**
     * 新增一体打印机服务点
     */
    @PreAuthorize("@ss.hasPermi('system:ytjpos:add')")
    @Log(title = "一体打印机服务点管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody NjtYtjPos njtYtjPos)
    {
        njtYtjPos.setCreateBy(getUsername());
        return toAjax(njtYtjPosService.insertNjtYtjPos(njtYtjPos));
    }

    /**
     * 修改一体打印机服务点
     */
    @PreAuthorize("@ss.hasPermi('system:ytjpos:edit')")
    @Log(title = "一体打印机服务点管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody NjtYtjPos njtYtjPos)
    {
        njtYtjPos.setUpdateBy(getUsername());
        return toAjax(njtYtjPosService.updateNjtYtjPos(njtYtjPos));
    }

    /**
     * 删除一体打印机服务点
     */
    @PreAuthorize("@ss.hasPermi('system:ytjpos:remove')")
    @Log(title = "一体打印机服务点管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(njtYtjPosService.deleteNjtYtjPosByIds(ids));
    }
}
