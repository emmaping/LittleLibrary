/********************************************************************
 * File Name:    LibraryController.java
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
import com.littlelibrary.service.LibraryService;

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
public class LibraryController
{
  @Autowired
  private LibraryService libraryService;

  @RequestMapping(value = "/library/list", method = RequestMethod.GET)
  public List<Library> listLibrary()
  {
    return libraryService.findAll();
  }

  @RequestMapping(value = "/library/add", method = RequestMethod.POST)
  public Library createLibrary(@RequestBody Library library)
  {
    return libraryService.add(library);
  }

  @RequestMapping(value = "/library/update", method = RequestMethod.PUT)
  public Library updateLibrary(@RequestBody Library library)
  {
    return libraryService.update(library);
  }

  @RequestMapping(value = "/library/delete/{id}", method = RequestMethod.DELETE)
  public void deleteLibrary(@PathVariable String id)
  {
    libraryService.deleteLibrary(id);
  }

  @RequestMapping(value = "/library/get/{id}", method = RequestMethod.GET)
  public Library getLibraryById(@PathVariable String id)
  {
    return libraryService.getLibraryById(id);
  }

}
