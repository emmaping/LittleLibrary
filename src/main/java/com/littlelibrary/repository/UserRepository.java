/********************************************************************
 * File Name:    UserRepository.javapublic Employee findByNameAndPassword(String name, String password);
 *
 * Date Created: 2014年12月9日
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2010 Symantec Corporation. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.littlelibrary.repository;

import com.littlelibrary.domain.User;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * TODO: Update with a detailed description of the interface/class.
 *
 */
public interface UserRepository extends MongoRepository<User, String>
{
  public User findByUserName(String userName);

  public User findByUserNameAndPassword(String username, String password);

  public User findByUserId(String userId);
}
