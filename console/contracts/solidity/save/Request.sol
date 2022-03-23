pragma solidity ^0.4.1;

import "./Authentication.sol";
//import "./LibBytes32Set.sol";
contract Request is Authentication{
    
    //LibBytes32Set.Bytes32Set private SIG;
    
    bool passed = false;
    
    struct SaveRequest{
        bytes32 hash;
        bytes32[] SIG;
        address creator;
        bool passed;
    }
    mapping(bytes32=>SaveRequest) private _saveRequests;
    
    function createSaveRequest(bytes32 hash, bytes32[] SIG, address creator) public auth{
        //require(_saveRequests[FP].FP == 0, "request already existed");
        _saveRequests[hash].hash = hash;
        _saveRequests[hash].SIG = SIG;
        _saveRequests[hash].creator = creator;
    }
    

    
    
    function getRequestData(bytes32 hash) public view 
      returns(bytes32, bytes32[] SIG, address creator, bool passed){
        SaveRequest storage request = _saveRequests[hash];
        require(_saveRequests[hash].hash == hash, "request not found");
        return (hash, request.SIG, request.creator, request.passed);
    }
    
    
    function deleteSaveRequest(bytes32 hash) public auth {
        require(_saveRequests[hash].hash == hash, "request not found");
        delete _saveRequests[hash];
    }
    
    
    
}