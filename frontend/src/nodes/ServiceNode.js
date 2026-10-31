import React from 'react';
import { Handle, Position } from '@xyflow/react';
import { Server } from 'lucide-react';

export default function ServiceNode({ data }) {
  return (
    <div className="px-4 py-3 shadow-lg rounded-lg bg-slate-900 border border-purple-500/50 min-w-[160px]">
      <div className="flex items-center gap-3">
        <div className="rounded-md w-8 h-8 flex items-center justify-center bg-purple-500/20 text-purple-400">
          <Server size={18} />
        </div>
        <div className="flex flex-col">
          <span className="text-slate-100 font-medium text-sm tracking-wide">{data.label}</span>
          {data.protocol && <span className="text-slate-500 text-xs mt-0.5">{data.protocol}</span>}
        </div>
      </div>
      <Handle type="target" position={Position.Top} className="w-2 h-2 bg-purple-500 border-none" />
      <Handle type="source" position={Position.Bottom} className="w-2 h-2 bg-purple-500 border-none" />
    </div>
  );
}