/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.muqui.helper;

import java.sql.Timestamp;

/**
 *
 * @author mq12
 */
public class FechaLimite {
    public int Limite(Timestamp limite) {
       java.util.Date date = new java.util.Date();
       Timestamp actual = new Timestamp(date.getTime());
        long l1 = limite.getTime();
        long l2 = actual.getTime();
        if (l2 > l1) {
            return 1;
        } else if (l1 > l2) {
            return -1;
        } else {
            return 0;
        }
    }
}
