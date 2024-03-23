package org.corejava;

import java.util.*;

public class BuyOneGetFree {
    public static void main(String[] args) {
        // Sample product price lists
        List<Integer> priceList1 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 60));
        List<Integer> priceList2 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 50, 60));

        //Offer Rule 1:
        offerStart("01");
        applyOffer_01(priceList1);
        applyOffer_01(priceList2);


        //Offer Rule 2:
        offerStart("02");
        priceList1 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 40, 50, 60, 60));
        priceList2 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50, 50, 50, 60));
        applyOffer_02(priceList1);
        applyOffer_02(priceList2);


        //Offer Rule 3:
        offerStart("03");
        priceList1 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 40, 50, 60, 60));
        priceList2 = new ArrayList<>(Arrays.asList(5, 5, 10, 20, 30, 40, 50, 50, 50, 60));
        applyOffer_03(priceList1);
        applyOffer_03(priceList2);
    }

    public static void applyOffer_01(List<Integer> productPriceList) {
        List<Integer> payableItems = new ArrayList<>();
        List<Integer> discountedItems = new ArrayList<>();

        generateInput(productPriceList);

        // Sort in descending order
        Collections.sort(productPriceList, Collections.reverseOrder());

        //get the payable items
        for (int i = 0; i < productPriceList.size(); i += 2) {
            payableItems.add(productPriceList.get(i));
        }

        for (int i = 1; i < productPriceList.size(); i += 2) {
            discountedItems.add(productPriceList.get(i));
        }
        generateOutput(discountedItems,payableItems);

    }


    private static void applyOffer_02(List<Integer> productPriceList) {
        List<Integer> payableItems = new ArrayList<>();
        List<Integer> discountedItems = new ArrayList<>();

        generateInput(productPriceList);

        // Sort in descending order
        Collections.sort(productPriceList, Collections.reverseOrder());


        while (productPriceList.size() >= 2) {
            //if pair found skip and check for next item as discounted item
            if (checkPairForPayableItem(productPriceList)) {

                //How to handle the situation when index out of bound may occur??????????
                payableItems.add(productPriceList.get(0));
                discountedItems.add(productPriceList.get(2));

                //remove items from list in case of pair found
                productPriceList.remove(0);//remove first
                if (productPriceList.size() > 1) { //if allows
                    productPriceList.remove(1);//remove non pair item
                }


            } else { // pair is not present remove two items and put them into the list
                payableItems.add(productPriceList.get(0));
                discountedItems.add(productPriceList.get(1));

                //remove items from list
                productPriceList.removeFirst();
                if (productPriceList.size() > 1) {
                    productPriceList.removeFirst();
                }
            }
        }

        generateOutput(discountedItems,payableItems);

    }

    /**
     * This method check for two subsequent item as equal or not.
     *
     * @param productPriceList It holds the int list.
     * @return True : If pair found <br>
     * false : if not
     */
    private static boolean checkPairForPayableItem(List<Integer> productPriceList) {
        return ((1) < productPriceList.size()) &&
                Objects.equals(productPriceList.get(0), productPriceList.get(1));

    }

    /**
     * This will look for pairs in subsequent positions in the price list if found will consider them as payable item.
     * Insert pair in the payable list and insert next two items in the discount list.
     * if no pair just consider it as a single item buy and insert first item in payable and next in discount item list.
     *
     * @param productPriceList It holds the product price list
     */
    private static void applyOffer_03(List<Integer> productPriceList) {
        List<Integer> discountedItems = new ArrayList<>();
        List<Integer> payableItems = new ArrayList<>();

        generateInput(productPriceList);

        // Sort in descending order
        Collections.sort(productPriceList, Collections.reverseOrder());

        while (!productPriceList.isEmpty()) {

            //pair found of equal value : add two subsequent item from the list as discounted items
            if (checkPairForPayableItem(productPriceList)) {
                safeDeletion(productPriceList, payableItems, 2);
                safeDeletion(productPriceList, discountedItems, 2);
            } else {// no pair found just remove single item and perform insertion.
                safeDeletion(productPriceList, payableItems, 1);
                safeDeletion(productPriceList, discountedItems, 1);
            }
        }

       generateOutput(discountedItems,payableItems);
    }

    private static void safeDeletion(List<Integer> primaryList, List<Integer> insertionList, int deleteCount) {

        while (!primaryList.isEmpty() && deleteCount >= 1) {
            deleteCount--;
            insertionList.add(primaryList.remove(0));
        }

    }

    private static void generateInput(List<Integer> input) {
        System.out.println("\nPrice List: " + input);
    }
    private static void generateOutput(List<Integer> discountedItems,List<Integer> payableItems) {
        System.out.println("Discounted Items: " + discountedItems);
        System.out.println("Payable Items: " + payableItems);
    }

    private static void offerStart(String offerNumber) {
        System.out.println("\n\n");
        System.out.println("******            OFFER " + offerNumber + " START                   *****************");
        System.out.println("******************************************************************");
        System.out.println("******************************************************************");
        System.out.println("******************************************************************\n");
        System.out.println();
    }
}