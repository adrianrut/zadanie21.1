package pl.rutkowski.zadanie21_1;

public enum Category {
   GROCERIES("Artykuły spożywcze"), AGD("Artykuły gospodarstwa domowego"), OTHER("Inne");

   private final String description;

   Category(String description) {
      this.description = description;
   }
   public String getDescription() {
      return description;
   }
}
