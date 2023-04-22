package co.edu.uniquindio.homebliss.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderator")
@PreAuthorize("hasAuthority('MODERATOR')")
public class ModeratorController {
}
