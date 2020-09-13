var app = new  Vue({
	
  el:'#app',	
  data() {
		return {
			message:'eita',
			produtos:[],
			produto:{},
			categorias:[],
			funcionarios:[],
			file:null
		};
	},
	filters: {
	  currencydecimal (value) {
	    return value.toFixed(2)
	  }
	},
  methods: {
		loadProduto: function(){
			axios.get('/produto')
			 .then(response => (this.produtos = response.data));
		},
		loadCategoria: function(){
			axios.get('/categoria')
			 .then(response => (this.categorias = response.data));
		},
		loadFuncionarios: function(){
			axios.get('/funcionario')
			 .then(response => (this.funcionarios = response.data));
		},
		selecionaProduto: function(id) {
			axios.get('/produto/' + id)
			 .then(response => (this.produto = response.data));
		},
		salvarProduto: function(e){
			console.log('vai salva o produto');
			var txMethod = "post";
			var txUrl = "/produto";
			if (this.produto.id) {
				txMethod = "put";
				txUrl = txUrl + "/" + this.produto.id;
			} 
			var formData = new FormData();
			formData.append("file", document.querySelector('#foto').files[0]);
			formData.append("produto", new Blob([JSON.stringify(this.produto)], {type: "application/json"}));
			console.log('formDAta', formData);	
			axios({
				method:txMethod, 
				url:txUrl, 
				data:formData,
				headers: { 'Content-Type': 'multipart/form-data' } 
				} ).then(response => {
					this.produto = response.data;
					this.loadProduto();
				});
		},
   },
   mounted(){
	  this.loadProduto();
	  this.loadCategoria();
	  this.loadFuncionarios();
   }
});