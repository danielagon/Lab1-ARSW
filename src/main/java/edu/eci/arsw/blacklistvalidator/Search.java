/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;

/**
 *
 * @author 2109734
 */
public class Search extends Thread{
    
    private final int start;
    private final int end;
    private final String host;
    private LinkedList<Integer> blackList = new LinkedList<>();
    private int ocurrencesCount;
    private int checkedListsCount;
    
    private HostBlacklistsDataSourceFacade skds = HostBlacklistsDataSourceFacade.getInstance();
    
    public Search(int start,int end,String host){
        this.start = start;
        this.end = end;
        this.host = host;
        ocurrencesCount = 0;
        checkedListsCount = 0;        
    }
    
    @Override
    public void run(){
        for (int i=start;i<end;i++){
            checkedListsCount++;
            if (skds.isInBlackListServer(i, host)){
                blackList.add(i);
                ocurrencesCount++;
            }
        }
    }
    
    public int getOcurrencesCount(){
        return ocurrencesCount;
    }
    
    public int getCheckedListCount(){
        return checkedListsCount;
    }
    
    public LinkedList<Integer> getBlackList(){
        return blackList;
    }   
}