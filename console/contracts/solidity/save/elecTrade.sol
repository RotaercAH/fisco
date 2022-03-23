pragma solidity ^0.4.1;


import "./Request.sol";
import "./EvidenceRepository.sol";
import "./Trade.sol";

contract elecTrade{
    Request public _requestRepo;
    EvidenceRepository public _evidenceRepo;
    Trade public _tradeRepo;
    //LibBytes32Set.Bytes32Set private Sig;
    
    event CreateSaveRequest(bytes32 indexed hash,bytes32[] SIG ,address creator);   
    event oprateSaveRequest(bytes32 indexed hash);
    event EvidenceSaved(bytes32 hash,bytes32[] FP,bytes32[] RP, bytes32[] Comm, bytes32[] Encript,address creator,uint timeStamp);

    
    constructor() public{
        _requestRepo = new Request();
        _evidenceRepo = new EvidenceRepository();
        _tradeRepo = new Trade();
    }
    
    
    //bytes32 hash      Bytes32Set FP
    modifier validateHash(bytes32 hash){
      require(hash != 0, "Not valid hash");
      _;
    }
    
    
    function createSaveRequest(bytes32 hash,bytes32[] SIG,address creator) public validateHash(hash){
        _requestRepo.createSaveRequest(hash, SIG, msg.sender);
        emit CreateSaveRequest(hash, SIG , msg.sender);
    }

    
    function oprateEvidence(bytes32 hash,bytes32[] FP,bytes32[] RP, bytes32[] Comm, bytes32[] SIG,bytes32[] Encript,address creator,uint timeStamp){
        _evidenceRepo.setData(hash,FP,RP,Comm,SIG,Encript,creator,timeStamp);
        emit EvidenceSaved(hash,FP,RP,Comm,Encript,creator,timeStamp);
    }
    

    
    function getRequestData(bytes32 hash) public view 
      returns(bytes32 ,bytes32[] SIG,address creator,bool passed){
        return _requestRepo.getRequestData(hash);
    }
    
    
    
    function getEvidence(bytes32 hash) public view returns(bytes32,bytes32[],bytes32[],bytes32[], bytes32[],bytes32[],address, uint){
        return _evidenceRepo.getData(hash);
    }
    
    
    
    
    
    
}