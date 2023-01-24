using APICatalogo.Context;
using APICatalogo.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace APICatalogo.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ProdutosController : ControllerBase
    {
        private readonly AppDbContext _appDbContext;

        public ProdutosController(AppDbContext appDbContext)
        {
            _appDbContext = appDbContext;
        }

        [HttpGet]
        public ActionResult<IEnumerable<Produto>> Get()
        {
            var produtos = _appDbContext.Produtos.ToList();
            if(produtos is null)
            {
                return NotFound("Produtos não encontrados ...");
            }

            return produtos;
        }

        [HttpGet("{id}",Name="ObterProduto")]
        public ActionResult<Produto> GetProduto(int id)
        {
            var produto = _appDbContext.Produtos.FirstOrDefault(p => p.CategoriaId == id);
            if(produto is null)
            {
                return NotFound("Este Produto não existe..");
            }
            return produto;
        }

        [HttpPost]
        public ActionResult Post(Produto produto)
        {
            if(produto is null)
            {
                return BadRequest("Produto Invalido");
            }
            _appDbContext.Produtos.Add(produto);
            _appDbContext.SaveChanges();

            return new CreatedAtRouteResult("ObterProduto", new { id = produto.ProdutoId }, produto);
        }
        [HttpPut("{id}")]
        public ActionResult Put( int id, Produto produto)
        {
            if(id != produto.ProdutoId)
            {
                return BadRequest();
            }
            _appDbContext.Entry(produto).State = EntityState.Modified;
            _appDbContext.SaveChanges();

            return Ok(produto);
        }
        [HttpDelete("{id}")]
        public ActionResult Delete(int id)
        {
            var produto = _appDbContext.Produtos.FirstOrDefault(p => p.ProdutoId == id);
            if(produto is null)
            {
                return NotFound("Produto não encontrado..");
            }
            _appDbContext.Produtos.Remove(produto);
            _appDbContext.SaveChanges();
            return Ok(produto);


        }

    }
}
