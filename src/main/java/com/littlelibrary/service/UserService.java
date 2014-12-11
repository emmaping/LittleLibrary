/********************************************************************
 * File Name:    UserService.java
 *
 * Date Created: 2014年12月9日
 *
 * ------------------------------------------------------------------
 * Copyright (C) 2010 Symantec Corporation. All Rights Reserved.
 *
 *******************************************************************/

// PACKAGE/IMPORTS --------------------------------------------------
package com.littlelibrary.service;

import com.littlelibrary.domain.User;
import com.littlelibrary.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
  @Autowired
  private UserRepository repo;

  @Autowired
  public UserService(UserRepository userRepository)
  {
    this.repo = userRepository;
  }

  public List<User> findAll()
  {
    return repo.findAll();
  }

  public User add(User user)
  {
    if (null == user)
    {
      return null;
    }

    User olduser = repo.findByUserName(user.getUserName());
    if (null == olduser)
    {
      return repo.save(user);
    }

    if (olduser.equals(user))
    {
      return user;
    }

    return repo.save(user);
  }

  public User update(User user)
  {
    if (null == user)
    {
      return null;
    }

    User olduser = repo.findOne(user.getUserId());
    if (null == olduser)
    {
      return repo.save(user);
    }

    if (olduser.equals(user))
    {
      return user;
    }

    return repo.save(user);
  }

  public User getUserByUserName(String userName)
  {

    if ((userName != null) && (userName.length() != 0))
    {
      return repo.findByUserName(userName);
    }

    return null;
  }

  public User getUserById(String userId)
  {
    return repo.findOne(userId);
  }

  public String getUserIdByUserName(String userName)
  {

    if ((userName != null) && (userName.length() != 0))
    {
      User user = repo.findByUserName(userName);
      return user.getUserId();
    }

    return null;
  }

  public void deleteUser(String userId)
  {

    repo.delete(userId);
  }

  public User login(String userName, String password)
  {
    if (userName == null || userName.length() == 0 || password == null || password.length() == 0)
    {
      return null;
    }
    User user = repo.findByUserNameAndPassword(userName, password);

    if (user == null)
    {
      return null;
    }

    return user;

  }

}
