package com.rostylka.newlib.controllers;

import com.rostylka.newlib.mappers.RoleMapper;
import com.rostylka.newlib.models.Role;
import com.rostylka.newlib.services.implementations.RoleServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String createUser(@ModelAttribute("role") Role role) {
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

    @Autowired
    public void setRoleServiceImplementation(RoleServiceImplementation roleServiceImplementation) {
        this.roleServiceImplementation = roleServiceImplementation;
    }
}
