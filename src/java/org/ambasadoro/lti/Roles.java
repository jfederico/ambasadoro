/*
 * Copyright (c) 2013
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ambasadoro.lti;

/**
 * LTI Role definitions.
 * 
 * @author Jesus Federico
 */
public class Roles {

    public static String STUDENT = "Student";
    public static String FACULTY = "Faculty";
    public static String MEMBER = "Member";
    public static String LEARNER = "Learner";
    public static String INSTRUCTOR = "Instructor";
    public static String MENTOR = "Mentor";
    public static String STAFF = "Staff";
    public static String ALUMNI = "Alumni";
    public static String PROSPECTIVESTUDENT = "ProspectiveStudent";
    public static String GUEST = "Guest";
    public static String OTHER = "Other";
    public static String ADMINISTRATOR = "Administrator";
    public static String OBSERVER = "Observer";
    public static String NONE = "None";
    
    public static String URN_SYSTEM_ROLE = "urn:lti:sysrole:ims/lis/";
    public static String URN_INSTITUTION_ROLE = "urn:lti:instrole:ims/lis/";
    public static String URN_CONTEXT_ROLE = "urn:lti:role:ims/lis/";

    public static boolean isLearner(String _roles){
        boolean response = false;
        
        String[] roles = _roles.split(",");
        for( int i=0; i < roles.length; i++){
            if( roles[i].equals(STUDENT) ||
                roles[i].equals(URN_INSTITUTION_ROLE + STUDENT) ||
                roles[i].equals(URN_CONTEXT_ROLE + STUDENT) ||
                roles[i].equals(LEARNER) ||
                roles[i].equals(URN_INSTITUTION_ROLE + LEARNER) ||
                roles[i].equals(URN_CONTEXT_ROLE + LEARNER)
                ){
                response = true;
                break;
            }
        }
        
        return response;
    }
    
    public static boolean isStudent(String _roles){
        return isLearner(_roles);
    }
    
    public static boolean isAdministrator(String _roles){
        boolean response = false;
        
        String[] roles = _roles.split(",");
        for( int i=0; i < roles.length; i++){
            if( roles[i].equals(ADMINISTRATOR) ||
                roles[i].equals(URN_INSTITUTION_ROLE + ADMINISTRATOR) ||
                roles[i].equals(URN_CONTEXT_ROLE + ADMINISTRATOR)
                ){
                response = true;
                break;
            }
        }
        
        return response;
    }
    
    public static boolean isAdmin(String _roles){
    	return isAdministrator(_roles);
    }

}
