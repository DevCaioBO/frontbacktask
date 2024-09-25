
import './style.css'
import { useEffect,useState,useRef } from 'react'
import Trash from '/src/assets/img/delete.png'
import api from '../../../services/api'
import baseURL from '../../../services/api'



function Home() {
  
  const [clientes,setClientes]=useState([])


  const inputName = useRef()
  const inputStats = useRef()

  

  async function getUsers(){
  const  clientesApi =  await api.get(`/tasks`)
  console.log(clientesApi.data)
   setClientes(clientesApi.data)
    
    

  }
  useEffect(()=>{
    getUsers()
  },[])


  async function createUsers(){
     await api.post(`/tasks`,{

      nome: inputName.current.value,
      stats: inputStats.current.value
      
      
     })
     
     getUsers()
 
    }
    // useEffect(()=>{
    //   getUsers()
    // },[])


    async function deleteUsers(id){
      console.log("valor do id = " + id)
      await api.delete(`/tasks/${id}`)
      getUsers()

     }


  return (
    <div className="container">
      <form className='formHome'>
        <h1>Cadastro de atividade</h1>
        <input name="name"type="text" autoComplete='off' placeholder='Digite o nome' ref={inputName}/>
        <input name="stats"type="text" autoComplete='off' placeholder='Digite o estado'  ref={inputStats}/>

        <button type='button' onClick={createUsers}> cadastrar</button>

        
      </form>
    { clientes.map((client,index) =>(
      
      <div key={client.id} id="cardsContainer">
      <div  className='card' >
        <p>Nome: {client.nome}</p>
        <p>Estado: {client.stats}</p>      </div>
      <button type='button' onClick={()=> deleteUsers(client.id)}>
        <img src={Trash} alt="" />
        </button>
    </div>
        

    ))}

    </div>



  )
}

export default Home
