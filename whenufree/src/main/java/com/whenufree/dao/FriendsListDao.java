package com.whenufree.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.whenufree.model.FriendsList;

@Repository
public interface FriendsListDao extends JpaRepository<FriendsList, FriendsList.FriendsListPK>{
    
}
