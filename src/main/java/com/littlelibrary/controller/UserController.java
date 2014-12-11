/********************************************************************
 * File Name:    UserController.java
 *
 * Date Created: 2014年12月9日
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2010 Symantec Corporation. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.littlelibrary.controller;

import com.littlelibrary.domain.Library;
import com.littlelibrary.domain.User;
import com.littlelibrary.service.LibraryService;
import com.littlelibrary.service.UserService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
@RestController
public class UserController
{
  @Autowired
  private UserService userService;

  @Autowired
  private LibraryService libraryService;

  @RequestMapping(value = "/user/{userId}/books", method = RequestMethod.GET)
  public List<Library> listUserBooksByUserId(@PathVariable String userId)
  {
    return libraryService.getLibraryByUserId(userId);
  }

  @RequestMapping(value = "/user/{userId}/borrow/{bookId}", method = RequestMethod.PUT)
  public void BorrowBooksById(@PathVariable String userId, @PathVariable String bookId)
  {
    Library library = libraryService.getLibraryById(bookId);
    User user = userService.getUserById(userId);
    if (library.getUser() == null)
    {
      List<User> users = new ArrayList<User>();
      users.add(user);
      library.setCurNumber(library.getCurNumber() - 1);
      library.setUser(users);
      libraryService.update(library);
    }
    else if (library.getUser().contains(user) != true)
    {
      List<User> users = library.getUser();
      users.add(user);
      library.setUser(users);
      library.setCurNumber(library.getCurNumber() - 1);
      libraryService.update(library);
    }

    return;
  }

  @RequestMapping(value = "/user/{userId}/return/{bookId}", method = RequestMethod.PUT)
  public void ReturnBooksById(@PathVariable String userId, @PathVariable String bookId)
  {
    Library library = libraryService.getLibraryById(bookId);
    User user = userService.getUserById(userId);
    if (library.getUser() != null && library.getUser().contains(user))
    {
      List<User> users = library.getUser();
      users.remove(user);
      library.setUser(users);
      library.setCurNumber(library.getCurNumber() + 1);
      libraryService.update(library);
    }

    return;
  }

  @RequestMapping(value = "/user/logon/{name}/{password}", method = RequestMethod.GET)
  public User login(@PathVariable String name, @PathVariable String password)
  {
    return userService.login(name, password);
  }

  @RequestMapping(value = "/user/add", method = RequestMethod.POST)
  public User createUser(@RequestBody User user)
  {
    return userService.add(user);
  }

}
