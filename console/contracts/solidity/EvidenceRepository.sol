pragma solidity ^0.4.25;
import "./Authentication.sol";

contract EvidenceRepository is Authentication {    
    struct EvidenceData{
        bytes32 hash;
        address owner;
        uint timestamp;
        bytes32[] ext;
    }
    mapping(bytes32=>EvidenceData) private _evidences;  
    
    function setData(bytes32 hash, address owner, uint timestamp, bytes32[] memory ext) public auth {
        _evidences[hash].hash = hash;
        _evidences[hash].owner = owner;
        _evidences[hash].timestamp = timestamp;
        _evidences[hash].ext = ext;
    }
    
    function getData(bytes32 hash) public view returns(bytes32 , address, uint, bytes32[]){
        EvidenceData storage evidence = _evidences[hash];
        require(evidence.hash == hash, "Evidence not exist");
        return (evidence.hash, evidence.owner, evidence.timestamp, evidence.ext);
    }
}
