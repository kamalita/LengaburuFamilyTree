/*
 * Copyright (c) 2020.    @author:KamalitaBiswas
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Geektrust {
    public static List<Member> familyList=new ArrayList<Member>();
    public Geektrust(){

        familyList.add(new Member(null,"King_Shan","Male",true,"Queen_Anga"));
        familyList.add(new Member(null,"Queen_Anga","Female",true,"King_Shan"));

        familyList.add(new Member("Queen_Anga","Chit","Male",true,"Amba"));
        familyList.add(new Member(null,"Amba","Female",true,"Chit"));

        familyList.add(new Member("Queen_Anga","Ish","Male",false,null));

        familyList.add(new Member("Queen_Anga","Vich","Male",true,"Lika"));
        familyList.add(new Member(null,"Lika","Female",true,"Vich"));

        familyList.add(new Member("Queen_Anga","Aras","Male",true,"Chitra"));
        familyList.add(new Member(null,"Chitra","Female",true,"Aras"));

        familyList.add(new Member("Queen_Anga","Satya","Female",true,"Vyan"));
        familyList.add(new Member(null,"Vyan","Male",true,"Satya"));

        familyList.add(new Member("Lika","Vila","Female",false,null));
        familyList.add(new Member("Lika","Chika","Female",false,null));

        familyList.add(new Member("Amba","Dritha","Female",true,"Jaya"));
        familyList.add(new Member(null,"Jaya","Male",true,"Dritha"));
        familyList.add(new Member("Dritha","Yodhan","Male",false,null));

        familyList.add(new Member("Amba","Tritha","Female",false,null));
        familyList.add(new Member("Amba","Vritha","Male",false,null));

        familyList.add(new Member("Chitra","Jnki","Female",true,"Arit"));
        familyList.add(new Member(null,"Arit","Male",true,"Jnki"));

        familyList.add(new Member("Jnki","Laki","Male",false,null));
        familyList.add(new Member("Jnki","Lavnya","Female",false,null));

        familyList.add(new Member("Chitra","Ahit","Male",false,null));

        familyList.add(new Member("Satya","Asva","Male",true,"Satvy"));
        familyList.add(new Member(null,"Satvy","Female",true,"Asva"));
        familyList.add(new Member("Satvy","Vasa","Male",false,null));


        familyList.add(new Member("Satya","Vyas","Male",true,"Krpi"));
        familyList.add(new Member(null,"Krpi","Female",true,"Vyas"));

        familyList.add(new Member("Krpi","Kriya","Male",false,null));
        familyList.add(new Member("Krpi","Krithi","Female",false,null));

        familyList.add(new Member("Satya","Atya","Female",false,null));
    }

    public static void main(String args[]) throws Exception {

        Geektrust family =new Geektrust();
        Scanner sc=new Scanner(System.in);
		
        String abPath = args[0];
        File file = new File(abPath);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while((st = br.readLine()) != null) {
            String input = st.replace("King Shan","King_Shan").replace("Queen Anga","Queen_Anga");
            String[] inputArr = input.split(" ");

            if (inputArr[0].contains(Querries.GET_RELATIONSHIP.toString()) ) {
                family.getRelation(inputArr);
            }
            else if (inputArr[0].contains(Querries.ADD_CHILD.toString())) {
                family.addChild(inputArr);
            }
            else if(inputArr[0].contentEquals("SHOW_ALL")){
                for(Member m:familyList){
                    System.out.print(m.getName()+"\t");
                }
            }
        }
    }

    private void addChild(String[] inputArr) {
        boolean isAdded=false;
        Member member;
        member=getMember(inputArr[1]);
        if(member==null){
            System.out.println(Messages.PERSON_NOT_FOUND);
            return;
        }
            if(member.getGender().contentEquals("Female") && member.isMarried() )
            {
                Member addMem = new Member(inputArr[1], inputArr[2], inputArr[3]);
                LinkedHashSet<Member> hashSet = new LinkedHashSet<Member>();
                if(hashSet.add(addMem))
                {
                    familyList.add(addMem);
                    isAdded=true;
                }else
                {
                    System.out.println(Messages.CHILD_EXISTS);                    
                }
            }


        System.out.println(isAdded? Messages.CHILD_ADDED: Messages.CHILD_ADD_FAILED);
    }
    private void getRelation(String[] inputArr) {
        Member member;
         member=getMember(inputArr[1]);
        if (member != null) {
            new Relations(inputArr[2], inputArr[1], member);
        } else {
            System.out.println(Messages.PERSON_NOT_FOUND);
        }
    }
    protected static Member getMember(String name){
        Member member = null;
        for(Member m: familyList)
        {
           if(m.getName().contentEquals(name))
           {
              int index = Geektrust.familyList.indexOf(m);
              member = Geektrust.familyList.get(index);
              break;
           }
        }
        return member;
    }
}
