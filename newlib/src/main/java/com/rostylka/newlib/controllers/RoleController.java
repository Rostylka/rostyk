package com.rostylka.newlib.controllers;

import com.rostylka.newlib.mappers.RoleMapper;
import com.rostylka.newlib.models.Role;
import com.rostylka.newlib.services.implementations.RoleServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {

    private RoleServiceImplementation roleServiceImplementation;

    /** GET form
     * CREATE Role
     * @param role
     * @return form for creation Role
     */
    @GetMapping("/new")
    public String newRole(@ModelAttribute("role") Role role) {
        return "roles/new";
    }

    /** POST Role
     * CREATE Role
     * @param role
     * @return list Roles
     */
    @PostMapping("/create")
    public String createRole(@ModelAttribute("role") Role role) {
        roleServiceImplementation.createRole(RoleMapper.mapToRoleDto(role));
        return "redirect:/roles";
    }

    /**
     * READ ALL Roles
     * @param model
     * @return list of Roles
     */
    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("roles",
                roleServiceImplementation.readAllRoles());
        return "roles/list";
    }

    /**
     * READ Role by ID
     * @param id - Role Id
     * @param model
     * @return Role by Id
     */
    @GetMapping("/{id}")
    public String readRollById(@PathVariable("id") int id, Model model) {
        model.addAttribute("role", roleServiceImplementation.readRoleById(id));
        return "roles/id";
    }

    /** GET form
     * UPDATE role by ID
     * @param id - Role Id
     * @param model
     * @return form for Updating Role Role
     */
    @GetMapping("update/{id}")
    public String readRoleForUpdate(@PathVariable("id") int id, Model model) {
        model.addAttribute("role", roleServiceImplementation.readRoleById(id));
        return "roles/update";
    }

    /**POST Update Role
     * UPDATE role by ID
     * @param id - path variable ID
     * @param role -
     * @return list Roles
     */
    @PostMapping("update/{id}")
    public String updateRole(@PathVariable("id") int id, @ModelAttribute("role") Role role) {
        roleServiceImplementation.updateRole(RoleMapper.mapToRoleDto(role));
        return "redirect:/roles";
    }

    /**
     * DELETE role by ID
     * @param id - path variable ID
     * @param role -
     * @return list Roles
     */
    @PostMapping("delete/{id}")
    public String deleteRole(@PathVariable("id") int id, @ModelAttribute("role") Role role) {
        roleServiceImplementation.delete(RoleMapper.mapToRoleDto(role));
        return "redirect:/roles";
    }

    @Autowired
    public void setRoleServiceImplementation(RoleServiceImplementation roleServiceImplementation) {
        this.roleServiceImplementation = roleServiceImplementation;
    }
}
