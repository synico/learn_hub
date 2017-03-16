define(["../_base/declare", "dojo/store/util/SimpleQueryEngine"],
  function(declare, SimpleQueryEngine) {

    declare("dojo.store.Memory", null, {
      constructor: function(options){},
      data: null,
      index: null,
      queryEngine: SimpleQueryEngine,
      idProperty: "id",
      get: function(id){},
    })
  }
);
