Ext.define('Apptest.view.fw.frameworkController.AggregateViewController', {
     extend: 'Apptest.view.fw.frameworkController.FrameworkViewController',
  
     
     /** To show the next card in aggregate form */
     showNextCard: function(btn) {

    	 var form = btn.up('form');
         if(form){          
              var grid = form.layout.getActiveItem().down('grid');               
                   if (!form.layout.getActiveItem().isValid()) {
                        if (grid == null || grid.store.getCount() == 0) return;
                   }     
         } else {
              form = btn.up('panel');
         }

         form.layout.setActiveItem(form.layout.getNext());
         prevBut = btn.up().down('#cardPrev');
         prevBut.setDisabled(false);
         if (!form.layout.getNext()) {
              btn.setDisabled(true);
              var saveBtn = form.down('#saveFormButton')
              if(saveBtn){
                   saveBtn.setDisabled(false);
              }
         }
     },
     
     /** To show the previous card in aggregate form */
     showPreviousCard: function(btn) {
    	 var form = btn.up('form');
         if(!form){
              form = btn.up('panel');
         } 

         form.layout.setActiveItem(form.layout.getPrev());
         nextBut = btn.up().down('#cardNext');
         nextBut.setDisabled(false);
         if (!form.layout.getPrev()) {
              btn.setDisabled(true);
         }
     },
     
     /** To show the first card in aggregate form */ 
     showFirstCard: function(form) {
          form.layout.setActiveItem(0);
          nextBut = form.down('#cardNext');
          if(nextBut){
        	  nextBut.setDisabled(false); 
          }
          
          prevBut = form.down('#cardPrev');
          if(prevBut){
        	  prevBut.setDisabled(true);
          }
     },
     
     /** To show the provide card in aggregate form */
     showCard: function(form, item) {
          form.layout.setActiveItem(item);
          if (!form.layout.getPrev()) {
               prevBut = form.down('#cardPrev');
               prevBut.setDisabled(true);
          } else {
               prevBut = form.down('#cardPrev');
               prevBut.setDisabled(false);
          }
          if (!form.layout.getNext()) {
               nextBut = form.down('#cardNext');
               nextBut.setDisabled(true);
          } else {
               nextBut = form.down('#cardNext');
               nextBut.setDisabled(false);
          }
     }
});