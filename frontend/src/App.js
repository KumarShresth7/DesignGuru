import React, { useCallback, useMemo } from 'react';
import {
  ReactFlow,
  MiniMap,
  Controls,
  Background,
  useNodesState,
  useEdgesState,
  addEdge,
} from '@xyflow/react';
import '@xyflow/react/dist/style.css';

import ServiceNode from './nodes/ServiceNode';
import DatabaseNode from './nodes/DatabaseNode';

const initialNodes = [
  { 
    id: '1', 
    type: 'service', 
    position: { x: 250, y: 100 }, 
    data: { label: 'API Gateway', protocol: 'HTTPS / WSS' } 
  },
  { 
    id: '2', 
    type: 'database', 
    position: { x: 250, y: 300 }, 
    data: { label: 'Users DB', engine: 'PostgreSQL' } 
  },
];

const initialEdges = [{ id: 'e1-2', source: '1', target: '2', animated: true, style: { stroke: '#94a3b8' } }];

export default function App() {
  const [nodes, setNodes, onNodesChange] = useNodesState(initialNodes);
  const [edges, setEdges, onEdgesChange] = useEdgesState(initialEdges);

  // Memoize nodeTypes so React Flow doesn't constantly remount the custom nodes
  const nodeTypes = useMemo(() => ({ service: ServiceNode, database: DatabaseNode }), []);

  const onConnect = useCallback(
    (params) => setEdges((eds) => addEdge({ ...params, animated: true, style: { stroke: '#94a3b8' } }, eds)),
    [setEdges],
  );

  return (
    <div style={{ width: '100vw', height: '100vh', backgroundColor: '#020617' }}>
      <ReactFlow
        nodes={nodes}
        edges={edges}
        nodeTypes={nodeTypes}
        onNodesChange={onNodesChange}
        onEdgesChange={onEdgesChange}
        onConnect={onConnect}
        colorMode="dark"
        fitView
      >
        <Controls className="bg-slate-900 border-slate-800 fill-slate-300" />
        <MiniMap nodeStrokeWidth={3} maskColor="#0f172a80" nodeColor="#334155" />
        <Background variant="dots" gap={16} size={1} color="#334155" />
      </ReactFlow>
    </div>
  );
}