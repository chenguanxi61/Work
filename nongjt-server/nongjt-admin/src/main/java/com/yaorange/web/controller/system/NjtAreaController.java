package com.yaorange.web.controller.system;

import java.util.List;

import com.yaorange.common.utils.poi.ExcelUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.yaorange.common.annotation.Log;
import com.yaorange.common.core.controller.BaseController;
import com.yaorange.common.core.domain.AjaxResult;
import com.yaorange.common.core.page.TableDataInfo;
import com.yaorange.common.enums.BusinessType;
import com.yaorange.system.domain.NjtArea;
import com.yaorange.system.service.INjtAreaService;

/**
 * 地区层级关系操作处理
 *
 * @author ruoyi
 */
@Tag(name = "地区层级关系管理")
@RestController
@RequestMapping("/system/area")
public class NjtAreaController extends BaseController {

    @Autowired
    private INjtAreaService njtAreaService;

    /**
     * 根据父编码查询子地区列表（级联核心接口）
     */
    @Operation(summary = "查询子地区列表")
    // @PreAuthorize("@ss.hasPermi('system:area:list')")  // 需要就放开
    @GetMapping("/list")
    public TableDataInfo list(@RequestParam(required = false) String parentCode) {
        startPage();
        List<NjtArea> list = njtAreaService.selectByParentCode(parentCode);
        return getDataTable(list);
    }

    /**
     * 导出地区数据
     */
    @Log(title = "地区管理", businessType = BusinessType.EXPORT)
    @Operation(summary = "导出地区数据")
    //@PreAuthorize("@ss.hasPermi('system:area:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, NjtArea area) {
        List<NjtArea> list = njtAreaService.selectByParentCode(area.getParentCode());
        ExcelUtil<NjtArea> util = new ExcelUtil<>(NjtArea.class);
        util.exportExcel(response, list, "地区数据");
    }

    /**
     * 根据主键获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:area:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable String id) {
        return success(njtAreaService.selectById(id));
    }

    /**
     * 新增地区
     */
    @PreAuthorize("@ss.hasPermi('system:area:add')")
    @Log(title = "地区管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody NjtArea area) {
        area.setCreateBy(getUsername());
        return toAjax(njtAreaService.insertArea(area));
    }

    /**
     * 修改地区
     */
    @PreAuthorize("@ss.hasPermi('system:area:edit')")
    @Log(title = "地区管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody NjtArea area) {
        area.setUpdateBy(getUsername());
        return toAjax(njtAreaService.updateArea(area));
    }

    /**
     * 删除地区
     */
    @PreAuthorize("@ss.hasPermi('system:area:remove')")
    @Log(title = "地区管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(njtAreaService.deleteByIds(ids));
    }
}