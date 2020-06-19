/*
 * Copyright (c) 2020.    @author:KamalitaBiswas
 */

import java.util.ArrayList;
import java.util.List;

public class Relations
{
    Relations(String relation,String name,Member mName)
    {
        //System.out.print(relation+" : ");
        switch(relation){
            case "Siblings":
                displayRelation(getSiblings(mName,false,false));
                break;
            case "Son":
                displayRelation(getChildren(mName, true));
                break;
            case "Daughter":
                displayRelation(getChildren(mName, false));
                break;
            case "Brother-In-Law":
                displayRelation(getSiblingInLaws(mName,true));
                break;
            case "Sister-In-Law":
                displayRelation(getSiblingInLaws(mName,false));
                break;
            case "Maternal-Aunt":
                displayRelation(getSecondGenerationRelationship(mName,true, false));
                break;
            case "Paternal-Aunt":
                displayRelation(getSecondGenerationRelationship(mName,false, false));
                break;
            case "Maternal-Uncle":
                displayRelation(getSecondGenerationRelationship(mName,true, true));
                break;
            case "Paternal-Uncle":
                displayRelation(getSecondGenerationRelationship(mName,false, true));
                break;
            default:
                System.out.println("not yet developed");
        }
    }

    private List<Member> getSecondGenerationRelationship(Member mName, boolean isMaternal, boolean isUncle) {
        List<Member> parentSiblings = new ArrayList<Member>();
                    String parentName=mName.getMother();
                    if(mName.getMother()!=null)
                    {
                        if(!isMaternal)
                        {
                            for (Member familyMember : Geektrust.familyList) {
                                String spouse = familyMember.getSpouse();
                                if (spouse != null && spouse.equalsIgnoreCase(parentName)) {
                                    parentName = familyMember.getName();
                                    break;
                                }
                            }
                        }
                         parentSiblings = getSiblings(Geektrust.getMember(parentName),isUncle,!isUncle);
                    }
             return  parentSiblings;
    }

    private List<Member> getSiblingInLaws(Member mName, boolean isBrotherInLaw)
    {
        List<Member> siblinginLawList = new ArrayList<Member>();
        if(mName.isMarried()){
            String spouseName=mName.getSpouse();
            List<Member> spouseSiblings = getSiblings(Geektrust.getMember(spouseName),isBrotherInLaw,!isBrotherInLaw);
            for(Member m:spouseSiblings){
                siblinginLawList.add(m);
            }
        }
        // to find the sisters of spouse
        List<Member> siblings = getSiblings(Geektrust.getMember(mName.getName()),!isBrotherInLaw,isBrotherInLaw);
        for (Member m : siblings)
        {
            if(m.isMarried()){
                siblinginLawList.add(Geektrust.getMember(m.getSpouse()));
            }
        }
        return siblinginLawList;

    }


    private List<Member> getSiblings(Member mName,boolean onlyBros,boolean onlySis){

        List<Member> siblingList = new ArrayList<Member>();

                if(mName.getMother() == null)
                {
                    return siblingList;
                }

                for (Member m : Geektrust.familyList)
                {
                    if (m.getMother() != null && mName.getMother().equals(m.getMother())
                            && !m.getName().equals(mName.getName()) && !onlyBros &&  !onlySis)
                    {
                        siblingList.add(m);
                    }
                    else if (m.getMother() != null && mName.getMother().equals(m.getMother())
                            && !m.getName().equals(mName.getName()) && onlyBros && m.getGender().contentEquals("Male"))
                    {
                        siblingList.add(m);
                    }
                    else if(m.getMother() != null && (mName.getMother().equals(m.getMother())
                            && !m.getName().equals(mName.getName()) && onlySis && m.getGender().contentEquals("Female")))
                    {
                        siblingList.add(m);
                    }
                }
        return siblingList;
    }

    private List<Member> getChildren(Member mName,Boolean isSearchingSons)
    {
        List<Member> childrenList = new ArrayList<Member>();
                if (mName.isMarried())
                {
                    if (mName.getGender().contentEquals("Male")){
                        mName.setName(mName.getSpouse());
                    }

                    for (Member m : Geektrust.familyList)
                    {
                        if(m.getMother()==null){
                            continue;
                        }
                        if (m.getMother().contentEquals(mName.getName()) && m.getGender().contentEquals("Male") && isSearchingSons)
                        {
                            childrenList.add(m);
                        }
                        else if(m.getMother().contentEquals(mName.getName()) && m.getGender().contentEquals("Female") && !isSearchingSons){
                            childrenList.add(m);
                        }
                    }
                }
                return childrenList;
    }

    private void displayRelation(List<Member> list){
        if (list.isEmpty()) {
            System.out.print(Messages.NONE);
        } else {
            list.forEach(((x) -> System.out.print( x.getName()+"\t")));
        }
        System.out.println();
    }
}
