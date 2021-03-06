package org.example.controllers;


import org.example.models.Person;
import org.example.services.ItemService;
import org.example.services.PeopleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {
//    private final PersonDao personDao;
//    private final PersonValidator personValidator;
//
//    @Autowired
//    public PeopleController(PersonDao personDao, PersonValidator personValidator) {
//        this.personDao = personDao;
//        this.personValidator = personValidator;
//    }

    private final PeopleService peopleService;

    private final ItemService itemService;

    public PeopleController(PeopleService peopleService, ItemService itemService) {
        this.peopleService = peopleService;
        this.itemService = itemService;
    }

    @GetMapping()
    public String index(Model model){
//        model.addAttribute("people", personDao.index());
        model.addAttribute("people", peopleService.findAll());

        itemService.findByItemName("Airpods");
        itemService.findByOwner(peopleService.findAll().get(0));
        peopleService.test();
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
//        model.addAttribute("person", personDao.show(id));
       model.addAttribute("person", peopleService.findOne(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

//        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/new";
        }
//        personDao.save(person);
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable ("id") int id){
//        model.addAttribute("person", personDao.show(id));
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")  @Valid Person person, BindingResult bindingResult, @PathVariable("id") int id){
//        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors()){
            return "people/edit";
        }
//        personDao.update(id, person);
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
//        personDao.delete(id);
        peopleService.delete(id);
        return "redirect:/people";
    }
}
