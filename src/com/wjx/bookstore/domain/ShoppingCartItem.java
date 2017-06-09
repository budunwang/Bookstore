package com.wjx.bookstore.domain;

/**
 * Created by admin on 12/18/2016.
 */
public class ShoppingCartItem {
    /**
     * 封装了购物车中的商品, 包含对商品的引用以及购物车中该商品的数量
     *
     */
        private Book book;
        private int quantity;

        public ShoppingCartItem(Book book) {
            this.book = book;
            this.quantity = 1;
        }

        public Book getBook() {
            return book;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        /**
         * 返回该商品在购物车中的钱数
         * @return
         */
        public float getItemMoney(){
            return book.getPrice() * quantity;
        }

        /**
         * 使商品数量 + 1
         */
        public void increment(){
            quantity++;
        }
}
