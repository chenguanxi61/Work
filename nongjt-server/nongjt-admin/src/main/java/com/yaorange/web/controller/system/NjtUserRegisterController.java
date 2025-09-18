package com.yaorange.web.controller.system;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yaorange.common.annotation.Log;
import com.yaorange.common.core.controller.BaseController;
import com.yaorange.common.core.domain.AjaxResult;
import com.yaorange.common.enums.BusinessType;
import com.yaorange.system.domain.vo.NjtUserRegister ;
import com.yaorange.system.service.INjtUserRegisterService ;
import com.yaorange.common.utils.poi.ExcelUtil;
import com.yaorange.common.core.page.TableDataInfo;

/**
 * 用户注册信息Controller
 *
 * @author yanye
 * @date 2025-09-18
 */
@RestController
@RequestMapping("/nongjt/register")
public class NjtUserRegisterController extends BaseController
{
    @Autowired
    private INjtUserRegisterService njtUserRegisterService;

    /**
     * 查询用户注册信息列表
     */
    @PreAuthorize("@ss.hasPermi('nongjt:register:list')")
    @GetMapping("/list")
    public TableDataInfo list(NjtUserRegister njtUserRegister)
    {
        startPage();
        List<NjtUserRegister> list = njtUserRegisterService.selectNjtUserRegisterList(njtUserRegister);
        return getDataTable(list);
    }

    /**
     * 导出用户注册信息列表
     */
    @PreAuthorize("@ss.hasPermi('nongjt:register:export')")
    @Log(title = "用户注册信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NjtUserRegister njtUserRegister)
    {
        List<NjtUserRegister> list = njtUserRegisterService.selectNjtUserRegisterList(njtUserRegister);
        ExcelUtil<NjtUserRegister> util = new ExcelUtil<NjtUserRegister>(NjtUserRegister.class);
        util.exportExcel(response, list, "用户注册信息数据");
    }

    /**
     * 获取用户注册信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('nongjt:register:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(njtUserRegisterService.selectNjtUserRegisterById(id));
    }

    /**
     * 新增用户注册信息
     */
    @PreAuthorize("@ss.hasPermi('nongjt:register:add')")
    @Log(title = "用户注册信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NjtUserRegister njtUserRegister)
    {
        return toAjax(njtUserRegisterService.insertNjtUserRegister(njtUserRegister));
    }

    /**
     * 修改用户注册信息
     */
    @PreAuthorize("@ss.hasPermi('nongjt:register:edit')")
    @Log(title = "用户注册信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NjtUserRegister njtUserRegister)
    {
        return toAjax(njtUserRegisterService.updateNjtUserRegister(njtUserRegister));
    }

    /**
     * 删除用户注册信息
     */
    @PreAuthorize("@ss.hasPermi('nongjt:register:remove')")
    @Log(title = "用户注册信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(njtUserRegisterService.deleteNjtUserRegisterByIds(ids));
    }
}
