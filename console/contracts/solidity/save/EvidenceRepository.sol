pragma solidity ^0.4.1;
import "./Authentication.sol";
//import "./LibBytes32Set.sol";

contract EvidenceRepository is Authentication {    
    
    //using LibBytes32Set for LibBytes32Set.Bytes32Set;
    
    
    struct EvidenceData{
        bytes32 hash;
        bytes32[] FP;
        bytes32[] RP;
        bytes32[] Comm;
        bytes32[] SIG;
        bytes32[] Encript;
        address owner;
        uint timestamp;
    }
    mapping(bytes32=>EvidenceData) private _evidences;  
    
    /*FP1： 长度为32的bytes数组
    function initFP(bytes32[] FP1) returns(LibBytes32Set.Bytes32Set FP){
        for(uint i = 0; i<FP1.length()-1;i++){
            FP.add(FP1[i]);
        }
    }
    
    
    function initRP(bytes32[] RP1) returns(LibBytes32Set.Bytes32Set RP){
        for(uint i = 0; i<RP1.length()-1;i++){
            RP.add(RP1[i]);
        }
    }
    
    function initComm(bytes32[] Comm1) returns(LibBytes32Set.Bytes32Set Comm){
        for(uint i = 0; i<Comm1.length()-1;i++){
            Comm.add(Comm[i]);
        }
    }
    
    function initEncript(bytes32[] Encript1) returns(LibBytes32Set.Bytes32Set Encript){
        for(uint i = 0; i<Encript1.length()-1;i++){
            Encript.add(Encript[i]);
        }
    }
    */
    function setData(bytes32 hash, bytes32[] FP,bytes32[] RP,bytes32[] Comm,bytes32[] SIG, bytes32[] Encript, address owner, uint timestamp) public auth {
        _evidences[hash].hash = hash;
        _evidences[hash].FP = FP;
        _evidences[hash].RP = FP;
        _evidences[hash].Comm = Comm;
        _evidences[hash].Encript = Encript;
        _evidences[hash].owner = owner;
        _evidences[hash].timestamp = timestamp;
    }
    
    function getData(bytes32 hash) public view returns(bytes32,bytes32[],bytes32[],bytes32[], bytes32[],bytes32[],address, uint){
        EvidenceData storage evidence = _evidences[hash];
        require(evidence.hash == hash, "Evidence not exist");
        return (evidence.hash,evidence.FP, evidence.RP,evidence.Comm, evidence.SIG, evidence.Encript,evidence.owner,evidence.timestamp);
    }
}
